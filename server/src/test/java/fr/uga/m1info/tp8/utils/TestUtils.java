package fr.uga.m1info.tp8.utils;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class TestUtils {

    public static <T> void verifyAnnotationClassFiledHaveProperty(Class<?> classz, String fieldClass, Class<? extends Annotation> annotationClass, String annotationFieldName, T value) throws Exception {
        assertionAnnotationClassFiledHaveProperty(classz, fieldClass, annotationClass, annotationFieldName)
                .as("Dans la classe %s, l'attribue %s n'est pas configuré correctement, la propriété %s de l'annotation @%s est incorrect.", classz.getSimpleName(), fieldClass, annotationFieldName, annotationClass.getSimpleName())
                .isEqualTo(value);
    }

    public static <T> void verifyClassHaveAnnotationField(Class<?> classz, Class<? extends Annotation> annotationClass, String annotationField, T value) throws Exception {
        assertionClassHaveAnnotationField(classz, annotationClass, annotationField)
                .as("L'attribue %s de l'annotation @%s de la class %s est incorrect", annotationField, annotationClass.getSimpleName(), classz.getSimpleName())
                .isEqualTo(value);
    }

    public static void verifyClassHaveAnnotation(Class<?> classz, Class<? extends Annotation> annotationClass) {
        assertThat(classz.getAnnotation(annotationClass))
                .as("La classe %s doit avoir l'annotation @%s", classz.getSimpleName(), annotationClass.getSimpleName())
                .isNotNull();
    }

    public static void verifyClassNotHaveAnnotation(Class<?> classz, Class<? extends Annotation> annotationClass) {
        assertThat(classz.getAnnotation(annotationClass))
                .as("La classe %s ne doit pas avoir l'annotation @%s", classz.getSimpleName(), annotationClass.getSimpleName())
                .isNull();
    }

    public static void verifyAllFieldIsDeclared(Class<?> classz, int nbFileds) {
        assertThat(classz.getDeclaredFields())
                .as("Tous les champs doivent être déclarés.")
                .hasSize(nbFileds);
    }

    public static void verifyClassFieldHaveAnnotation(Class<?> classz, String fieldClass, Class<? extends Annotation> annotationClass) throws Exception {
        assertThat(classz.getDeclaredField(fieldClass).getAnnotation(annotationClass))
                .as("Dans la classe %, l'attribue doit avoir l'annotation @%", classz.getSimpleName(), fieldClass, annotationClass.getSimpleName())
                .isNotNull();

    }

    public static void verifyClassFieldNotHaveAnnotation(Class<?> classz, String fieldClass, Class<? extends Annotation> annotationClass) throws Exception {
        assertThat(classz.getDeclaredField(fieldClass).getAnnotation(annotationClass))
                .as("Dans la classe %s, l'attribue %s n'a pas besoins de l'annotation @%s", classz.getSimpleName(), fieldClass, annotationClass.getSimpleName())
                .isNull();
    }


    public static void verifyMethodIsDeclared(Class<?> clazz, String methodNameContaining) {
        assertThat(Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).toList())
                .as("La méthod %s devrait être définie dans la class %s", methodNameContaining, clazz.getSimpleName())
                .isNotEmpty();
    }


    public static void verifyMethodHaveAnnotation(Class<?> clazz, String methodNameContaining, Class<? extends Annotation> annotationClass) {
        assertionClassHaveAnnotation(clazz, methodNameContaining, annotationClass)
                .as("La méthod %s devrait avoir l'annotation %s dans la class %s", methodNameContaining, annotationClass.getSimpleName(), clazz.getSimpleName())
                .isNotNull();
    }

    private static ObjectAssert<? extends Annotation> assertionClassHaveAnnotation(Class<?> clazz, String methodNameContaining, Class<? extends Annotation> annotationClass) {
        return assertThat(Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).findFirst()
                .orElseThrow()
                .getAnnotation(annotationClass));
    }

    public static <T> void verifyMethodAnnotationField(Class<?> clazz, String methodNameContaining, Class<? extends Annotation> annotationClass, String annotationField, T value) throws Exception {
        Method methodInInterface = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).findFirst().orElseThrow();
        Method m = methodInInterface
                .getAnnotation(annotationClass).getClass()
                .getMethod(annotationField);
        assertThat(m.invoke(methodInInterface.getAnnotation(annotationClass)))
                .as("Dans méthode %s, la propriété %s de l'annotation %s devrait être égale à [%s]", methodInInterface.toString(), annotationField, annotationClass.getSimpleName(), clazz.getSimpleName())
                .isEqualTo(value);
    }

    public static void verifyMethodHaveParam(Class<?> clazz, String methodNameContaining, String parameterNameContaining) {
        Method methodInInterface = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).findFirst().orElseThrow();
        assertThat(Arrays.stream(methodInInterface.getParameters()).filter(parameter ->
                parameter.getName().toLowerCase().contains(parameterNameContaining.toLowerCase())
        ))
                .as("La méthod %s devrait avoir un paramètre %s", methodInInterface.getName(), parameterNameContaining)
                .isNotEmpty();
    }

    public static void verifyMethodParamHaveAnnotation(Class<?> clazz, String methodNameContaining, String parameterNameContaining, Class<? extends Annotation> annotationClass) {
        Method methodInInterface = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).findFirst().orElseThrow();
        assertThat(Arrays.stream(methodInInterface.getParameters()).filter(parameter ->
                                parameter.getName().toLowerCase().contains(parameterNameContaining.toLowerCase())
                        ).findFirst()
                        .orElseThrow()
                        .getAnnotation(annotationClass)
        )
                .as("Le paramètre %s  de la méthod %s devrait avoir un l'annotation %s", parameterNameContaining, methodInInterface.getName(), annotationClass.getSimpleName())
                .isNotNull();
    }

    public static <T> void verifyMethodParamAnnotationField(Class<?> clazz, String methodNameContaining, String parameterNameContaining, Class<? extends Annotation> annotationClass, String annotationField, T value) throws Exception {
        Method methodInInterface = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).findFirst().orElseThrow();
        Annotation annotation = Arrays.stream(methodInInterface.getParameters()).filter(parameter ->
                        parameter.getName().toLowerCase().contains(parameterNameContaining.toLowerCase())
                ).findFirst()
                .orElseThrow()
                .getAnnotation(annotationClass);
        assertThat(annotation
                        .getClass()
                        .getMethod(annotationField)
                .invoke(annotation)

        )
                .as("Le valeur %s l'annotation %s du paramètre %s de la méthode %s",annotationField,annotationClass.getSimpleName(), parameterNameContaining, methodInInterface.getName())
                .isNotNull()
                .isEqualTo(value);
    }

    public static <T> void verifyApiResponse(Class<?> clazz, String methodNameContaining, int numberOfApiResponse) throws Exception {
        assertionClassHaveAnnotation(clazz, methodNameContaining, ApiResponses.class)
                .as("L'annotation @ApiResponse devrait être présente")
                .isNotNull();

        Method methodInInterface = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).findFirst().orElseThrow();

        Method apiResponsesMethod = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).findFirst()
                .orElseThrow()
                .getAnnotation(ApiResponses.class)
                .getClass()
                .getMethod("value");

        Assertions.assertThat((ApiResponse[]) apiResponsesMethod
                        .invoke(methodInInterface.getAnnotation(ApiResponses.class)))
                .as("Doit avoir au moins %n ApiResponse", numberOfApiResponse)
                .hasSize(numberOfApiResponse);
    }


    public static <T> void verifyApiResponseField(Class<?> clazz, String methodNameContaining, int index, String fieldName, T value) throws Exception {
        assertionClassHaveAnnotation(clazz, methodNameContaining, ApiResponses.class)
                .as("L'annotation @ApiResponse devrait être présente")
                .isNotNull();

        Method methodInInterface = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).findFirst().orElseThrow();

        Method apiResponsesMethod = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase())
                ).findFirst()
                .orElseThrow()
                .getAnnotation(ApiResponses.class)
                .getClass()
                .getMethod("value");

        Method apiResponseMethod = (((ApiResponse[]) apiResponsesMethod
                .invoke(methodInInterface.getAnnotation(ApiResponses.class)))[index].getClass()).getMethod(fieldName);

        assertThat(apiResponseMethod.invoke((((ApiResponse[]) apiResponsesMethod
                        .invoke(methodInInterface.getAnnotation(ApiResponses.class)))[index])))
                .as("Dans la classe %s, l'annotation ApiResponse de la méthod %s devrait avoir la propriété %s de cette manière [%s]", clazz.getSimpleName(), methodInInterface.getName(), fieldName, value)
                .isEqualTo(value);
    }

    public static ObjectAssert<Object> assertionClassHaveAnnotationField(Class<?> classz, Class<? extends Annotation> annotationClass, String annotationField) throws Exception {
        return assertThat(
                Arrays.stream(classz.getAnnotation(annotationClass)
                                .annotationType()
                                .getMethods())
                        .filter(method -> method.getName().equals(annotationField))
                        .findFirst()
                        .orElseThrow()
                        .invoke(classz.getAnnotation(annotationClass))

        );
    }

    public static ObjectAssert<Object> assertionAnnotationClassFiledHaveProperty(Class<?> classz, String fieldClass, Class<? extends Annotation> annotationClass, String annotationFieldName) throws Exception {
        return assertThat(Arrays.stream(Arrays.stream(Arrays.stream(classz.getDeclaredFields())
                                .filter(field -> field.getName().equals(fieldClass))
                                .findFirst()
                                .orElseThrow(() -> new Exception(String.format("L'attribue %s n'est pas déclaré dans la classe", fieldClass)))
                                .getAnnotationsByType(annotationClass))
                        .findFirst()
                        .orElseThrow(() -> new Exception(String.format("L'attribue %s n'a pas l'annotation @%s", fieldClass, annotationClass.getSimpleName())))
                        .annotationType()
                        .getMethods())
                .filter(method -> method.getName().equals(annotationFieldName))
                .findFirst()
                .orElseThrow()
                .invoke(classz.getDeclaredField(fieldClass).getAnnotation(annotationClass))
        );
    }

    public static EntityType<?> getEntityTypeByEntityManager(EntityManager entityManager, String entity) throws Exception {
        return entityManager.getMetamodel()
                .getEntities().stream().filter(entityType -> entityType.getName().toLowerCase().contains(entity.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("la classe contenant %s n'a pas été trouvé", entity)));
    }

    public static void verifyRepositoryExtendJpaRepositoryWith(Class<?> clazz, Class<?> entityClass, Class<?> idClass) {
        Arrays.stream(clazz.getGenericInterfaces())
                .filter(type -> type instanceof ParameterizedType parameterizedType && parameterizedType.getRawType() == JpaRepository.class)
                .map(type -> ((ParameterizedType) type).getActualTypeArguments())
                .forEach(repoTypes -> {
                    assertThat(repoTypes).hasSize(2);
                    assertThat(repoTypes[0].getTypeName())
                            .as("Le type de l'entité n'est pas correct dans le repository [%s]", clazz.getSimpleName())
                            .isEqualTo(entityClass.getName());
                    assertThat(repoTypes[1].getTypeName())
                            .as("Le type de l'id de l'entité n'est pas correct dans le repository [%s]", clazz.getSimpleName())
                            .isEqualTo(idClass.getName());
                });
    }
}
