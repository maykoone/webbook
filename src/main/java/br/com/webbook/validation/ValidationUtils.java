/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Path.Node;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import org.springframework.validation.Errors;

/**
 *
 * @author maykoone
 */
public class ValidationUtils {

    public static boolean isValid(Errors result, Object o, Class<?>... classes) {
        if (classes == null || classes.length == 0 || classes[0] == null) {
            classes = new Class<?>[]{Default.class};
        }
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(o, classes);
        for (ConstraintViolation<Object> v : violations) {
            Path path = v.getPropertyPath();
            String propertyName = "";
            if (path != null) {
                for (Node n : path) {
                    propertyName += n.getName() + ".";
                }
                propertyName = propertyName.substring(0, propertyName.length() - 1);
            }
            String constraintName = v.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
            if (propertyName == null || "".equals(propertyName)) {
                result.reject(constraintName, v.getMessage());
            } else {
                result.rejectValue(propertyName, constraintName, v.getMessage());
            }
        }
        return violations.isEmpty();
    }
}
