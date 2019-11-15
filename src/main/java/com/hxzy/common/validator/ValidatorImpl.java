package com.hxzy.common.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    /**
     *  这个方法会在ValidatorImpl实例化bean之后被回调 (后置增强)
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂的初始化 方式 实例化   @Validated
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    /**
     * 手动调用验证，并返回结果
     * @param bean
     * @return
     */
    public ValidatorResult validate(Object bean){
        ValidatorResult result = new ValidatorResult();

        //如果 传入的bean  定义的emm规则 有违背 了这个方法，那么返回结果里面就会有值
        Set<ConstraintViolation<Object>> validateSet = validator.validate(bean);
        if (validateSet.size()>0){
            //有错误
            result.setHasErrors(true);

            //原始的循环遍历
//            for (ConstraintViolation error : validateSet) {
//                //错误信息
//                String errMsg = error.getMessage();
//                //哪个字段错了，
//                String propertyName = error.getPropertyPath().toString();
//                result.getErroMsgMap().put(propertyName,errMsg);
//            }

            // jdk 1.8以后的  lambda表达式
            validateSet.forEach(error->{
                //错误信息
                String errMsg = error.getMessage();
                //哪个字段错了，
                String propertyName = error.getPropertyPath().toString();
                result.getErroMsgMap().put(propertyName,errMsg);
            });

        }
        return result;
    }





}
