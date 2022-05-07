package homework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MyTestFramework {
    public static Class<? extends Annotation> BEFORE;
    public static Class<? extends Annotation> AFTER;
    public static Class<? extends Annotation> TEST;

    static {
        try {
            BEFORE = (Class<? extends Annotation>) Class.forName("homework.annotations.Before");
            AFTER = (Class<? extends Annotation>) Class.forName("homework.annotations.After");
            TEST = (Class<? extends Annotation>) Class.forName("homework.annotations.Test");
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException(String.format("Не найдена аннотация!", e.getException()));
        }
    }

    public static void runTest(String testClassName) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(testClassName);

        Method[] declaredMethods = clazz.getDeclaredMethods();

        Set<Method> beforeMethods = getAnnotatedMethods(declaredMethods, BEFORE);
        Set<Method> afterMethods = getAnnotatedMethods(declaredMethods, AFTER);
        Set<Method> testMethods = getAnnotatedMethods(declaredMethods, TEST);
        if (testMethods.isEmpty()) {
            throw new RuntimeException("В классе не найдено ни одного теста!!!");
        }

        Optional<Integer> successTestsCount = executeTestsAndGetSuccessCount(clazz, testMethods, beforeMethods, afterMethods);
        if (successTestsCount.isEmpty()) {
            throw new RuntimeException("Ни одного теста не было выполнено успешно!!! Ужас.");
        }
        if (testMethods.size() > successTestsCount.get()) {
            String message = String.format("Не все тесты выполнены успешно! Успешно выполнено %s тестов из %s", successTestsCount.get(), testMethods.size());
            System.err.println(message);
            throw new TestFailedException(message);
        }
        System.out.println("Все тесты успешно выполнены! Ура.");
    }

    private static Set<Method> getAnnotatedMethods(Method[] methods, Class<?> annotationToCheck) {
        return Arrays.stream(methods)
                .filter(method -> Arrays.stream(method.getDeclaredAnnotations())
                        .map(Annotation::annotationType)
                        .anyMatch(annotationType -> annotationType.isAssignableFrom(annotationToCheck)))
                .collect(Collectors.toSet());
    }

    private static Optional<Integer> executeTestsAndGetSuccessCount(Class<?> clazz,
                                                                    Set<Method> testMethods,
                                                                    Set<Method> beforeMethods,
                                                                    Set<Method> afterMethods
    ) {
        return testMethods.stream()
                .map(method -> executeTestMethod(clazz, beforeMethods, afterMethods, method))
                .reduce(Integer::sum);
    }

    private static int executeTestMethod(Class<?> clazz, Set<Method> beforeMethods, Set<Method> afterMethods, Method method) {
        printHeader(method);

        Object instance = createNewInstance(clazz);

        beforeMethods.forEach(before -> invokeMethod(before, instance));
        int status = invokeMethod(method, instance);
        afterMethods.forEach(after -> invokeMethod(after, instance));

        printFooter(method);
        return status;
    }

    private static void printHeader(Method method) {
        System.out.println("===");
        System.out.println("Выполняем тест " + method.getName());
        System.out.println();
    }

    private static Object createNewInstance(Class<?> clazz) {
        Object instance = null;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private static int invokeMethod(Method method, Object instance) {
        System.out.println("Выполняем метод " + method.getName() + "...");
        try {
            method.invoke(instance);
        } catch (Exception e) {
            System.out.printf("Произошла ошибка при выполнении метода %s: %s", method.getName(), e.getMessage());
            System.out.println();
            return 0;
        }
        System.out.println("Метод " + method.getName() + " выполнен успешно.");
        return 1;
    }

    private static void printFooter(Method method) {
        System.out.println("Тест " + method.getName() + " выполнен");
        System.out.println("===");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
