package service;

import service.api.*;
import service.impl.*;

public class ServiceFactory {
    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return Holder.INSTANCE;
    }


    private final StatusService statusService =new StatusServiceImpl();
    private final MovieService movieService =new MovieServiceImpl();
    private final FeedbackService feedbackService =new FeedbackServiceImpl();
    private final UserService userService=new UserServiceImpl();
    private final RoleService roleService=new RoleServiceImpl();


    public FeedbackService getFeedbackService() {
        return feedbackService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public UserService getUserService() {
        return userService;
    }

    public StatusService getStatusService() {
        return statusService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    private static class Holder {
        static final ServiceFactory INSTANCE = new ServiceFactory();
    }
}
