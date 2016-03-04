package cn.edu.dhu.library.littlebee.controller;

import cn.edu.dhu.library.littlebee.entity.Resource;
import cn.edu.dhu.library.littlebee.service.ResourceService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by sherry on 15-12-29.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView formUpload(@RequestParam(defaultValue = "0") Integer page) {
        ModelAndView mav = new ModelAndView("resource/list");
        Page<Resource> AllResource = resourceService.getResourcesByType("Downloads", page, 15);
        List<Resource> resources = AllResource.getContent();
        Integer pageCount = AllResource.getTotalPages();
        Integer pageCur = page;
        mav.addObject("resources", resources);
        mav.addObject("pageCount", pageCount);
        mav.addObject("pageCur", pageCur);
        return mav;
    }

    @PreAuthorize("hasAuthority('manageResource')")
    @Transactional
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new FileUploadException("The file is empty.");
            }

            String digest = resourceService.calcDigest(file.getBytes());

            // metadata persistence
            Resource resource = resourceService.findByDigest(digest);
            if (resource == null) {
                resource = new Resource(file.getSize(), digest, file.getOriginalFilename(), file.getContentType());
            }
            System.out.println("Resource Digest: " + resource.getDigest());

            resourceService.saveFile(file.getBytes(), resource.getDigest(), file.getOriginalFilename());

            resource.setUrl("/resource/view/" + resource.getDigest());
            resource.setType("Downloads");

            resourceService.save(resource);
            logger.info("Resource Digest: '{}'", resource.getDigest());
            return "redirect:/resource/list";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PreAuthorize("hasAuthority('manageResource')")
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public Object uploadImage(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) {
        try {
            if (file.isEmpty()) {
                throw new FileUploadException("The file is empty.");
            }
            String digest = resourceService.calcDigest(file.getBytes());
            // metadata persistence
            Resource resource = resourceService.findByDigest(digest);
            if (resource == null) {
                resource = new Resource(file.getSize(), digest, file.getOriginalFilename(), file.getContentType());
            }
            System.out.println("Resource Digest: " + resource.getDigest());
            resourceService.saveFile(file.getBytes(), resource.getDigest(), file.getOriginalFilename());
            resource.setUrl("/resource/view/" + resource.getDigest());
            resource.setType("Photo");
            resourceService.save(resource);
            logger.info("Resource Digest: '{}'", resource.getDigest());
            // 最后返回一个URL给ckeditor;其中image/imagePath.png为image存后的路径；第一个参数来自前台的一个标志，第三个参数错误返回码
            //这里返回的JavaScript代码为ckeditor API中回调函数要求返回格式
            return "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("
                    + CKEditorFuncNum + ",'"  + resource.getUrl() + "', '');</script>";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "/view/{digest}", method = RequestMethod.GET)
    public void viewDigest(HttpServletResponse response, @PathVariable String digest) {
        try {
            Resource resource = resourceService.findByDigest(digest);
            File file = new File(resourceService.getFilePath(digest, resource.getName()));
            response.setContentType(resource.getContentType());
            response.setContentLength(resource.getSize().intValue());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + resourceService.getFilename(resource.getName()) + "\"");
            InputStream is = new FileInputStream(file);
            IOUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
            logger.error("Could not show resource " + digest, e);
            //throw new ResourceNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @PreAuthorize("hasAuthority('manageResource')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String editRemoveResource(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes,
                                     Model model) {
        if (resourceService.delete(id)) {
            redirectAttributes.addFlashAttribute("deletion", "success");
        } else {
            redirectAttributes.addFlashAttribute("deletion", "unsuccess");
        }
        return "redirect:/resource/list";
    }

}

