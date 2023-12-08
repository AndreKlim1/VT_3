package controller.command;

import controller.command.impl.*;
import controller.command.impl.transition.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put(CommandName.DEFAULT_COMMAND, new DefaultCommand());
        commands.put(CommandName.GO_MAIN_COMMAND, new GoToMainCommand());
        commands.put(CommandName.GO_PROFILE_COMMAND, new GoToProfileCommand());
        commands.put(CommandName.GO_LOG_UP_COMMAND, new GoToLogUpCommand());
        commands.put(CommandName.GO_CHANGE_MOVIE_COMMAND, new GoToChangeMovieCommand());
        commands.put(CommandName.GO_LOG_IN_COMMAND, new GoToLogInCommand());
        commands.put(CommandName.GO_MOVIE_INFO_COMMAND, new GoToMovieInfoCommand());
        commands.put(CommandName.GO_ADD_MOVIE_COMMAND, new GoToAddMovieCommand());

        commands.put(CommandName.ADD_FEEDBACK_COMMAND, new AddFeedbackCommand());
        commands.put(CommandName.LOG_OUT_COMMAND, new LogOutCommand());
        commands.put(CommandName.LOG_IN_COMMAND, new LogInCommand());
        commands.put(CommandName.LOG_UP_COMMAND, new LogUpCommand());
        commands.put(CommandName.ADD_MOVIE_COMMAND, new AddMovieCommand());
        commands.put(CommandName.BAN_USER_COMMAND, new BanUserCommand());
        commands.put(CommandName.CHANGE_FEEDBACK_COMMAND, new ChangeFeedbackCommand());
        commands.put(CommandName.CHANGE_MOVIE_COMMAND, new ChangeMovieCommand());
        commands.put(CommandName.CHANGE_NICKNAME_COMMAND, new ChangeUserNicknameCommand());
        commands.put(CommandName.CHANGE_STATUS_COMMAND, new ChangeUserStatusCommand());
        commands.put(CommandName.DELETE_FEEDBACK_COMMAND, new DeleteFeedbackCommand());
        commands.put(CommandName.DELETE_MOVIE_COMMAND, new DeleteMovieCommand());
    }

    public static CommandFactory getInstance() {
        return Holder.INSTANCE;
    }

    public Command getCommand(String name) {
        return Optional.ofNullable(commands.get(name)).orElse(commands.get(CommandName.DEFAULT_COMMAND));
    }

    private static class Holder {
        static final CommandFactory INSTANCE = new CommandFactory();
    }

}
