package cn.edu.dhu.library.littlebee.service;

import cn.edu.dhu.library.littlebee.entity.Activity;

import java.util.List;

/**
 * Created by sherry on 15-12-5.
 */
public interface ActivityService {

    List<Activity> getAllActivities();

    Activity findOne(Integer id);

    boolean save(Activity activity);

    boolean delete(Integer id);

    boolean editActivity(Activity editActivity);
}
