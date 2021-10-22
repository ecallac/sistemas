package com.security.web.constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

    private Boolean isOptional;
    private String pattern;

    @Override
    public void initialize(ValidDate validDate) {
        this.isOptional = validDate.optional();
        this.pattern = validDate.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        boolean validDate = isValidFormat(pattern, value);

        return isOptional ? (validDate || (value==null||value.equals("")?true:false)) : validDate;
    }

    private static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (value != null){
                date = sdf.parse(value);
                if (!value.equals(sdf.format(date))) {
                    date = null;
                }
            }

        } catch (ParseException ex) {
        }
        return date != null;
    }
}