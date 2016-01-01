package cn.edu.dhu.library.littlebee.service.impl;

import cn.edu.dhu.library.littlebee.entity.Activity;
import cn.edu.dhu.library.littlebee.repository.ActivityRepository;
import cn.edu.dhu.library.littlebee.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sherry on 15-12-5.
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    private Sort sortByTimeDesc() {
        return new Sort(Sort.Direction.DESC, "createdDate");
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll(sortByTimeDesc());
    }

    @Override
    public Activity findOne(Integer id) {
        return activityRepository.findOne(id);
    }

    @Override
    public boolean save(Activity activity) {
        if(activity != null){
            activityRepository.save(activity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Activity activity = activityRepository.findOne(id);
        if(activity != null){
            activityRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean editActivity(Activity editActivity) {
        if(editActivity != null){
            activityRepository.save(editActivity);
            return true;
        }
        return false;
    }

    @Override
    public Page<Activity> listOrderByTime(Integer page, int size) {
        Pageable pageable = new PageRequest(page, size, sortByTimeDesc());
        return activityRepository.findAll(pageable);
    }

}
