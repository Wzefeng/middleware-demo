package demo.spring.data.redis.util;

import java.util.function.Function;

import static demo.spring.data.redis.util.ExceptionUtils.wrapThrowable;

/**
 * A function interface for action with {@link Throwable}
 *
 * @see Function
 * @see Throwable
 */
@FunctionalInterface
public interface ThrowableAction {

    /**
     * Executes the action
     *
     * @throws Throwable if met with error
     */
    void execute() throws Throwable;

    /**
     * Executes {@link ThrowableAction}
     *
     * @param action {@link ThrowableAction}
     * @throws RuntimeException wrap {@link Exception} to {@link RuntimeException}
     */
    static void execute(ThrowableAction action) throws RuntimeException {
        execute(action, RuntimeException.class);
    }

    /**
     * Executes {@link ThrowableAction}
     *
     * @param action {@link ThrowableAction}
     * @throws T wrap {@link Throwable} to the specified {@link Throwable} type
     */
    static <T extends Throwable> void execute(ThrowableAction action, Class<T> throwableType) throws T {
        try {
            action.execute();
        } catch (Throwable e) {
            throw wrapThrowable(e, throwableType);
        }
    }
}

