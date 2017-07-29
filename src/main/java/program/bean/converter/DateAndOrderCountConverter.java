package program.bean.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import program.bean.StringAndNumber;

//@Component
public class DateAndOrderCountConverter implements Converter<String,StringAndNumber>,ConversionService {
//    @Resource
    GenericConversionService defaultConversionService;
    public DateAndOrderCountConverter(){
        System.out.println("init converter");
    }
    @Override
    public StringAndNumber convert(String s) {
        return new StringAndNumber();
    }
//    @PostConstruct
    public void init(){
        DefaultConversionService.addDefaultConverters(defaultConversionService);
        defaultConversionService.addConverter(this);
    }

    @Override
    public boolean canConvert(Class<?> aClass, Class<?> aClass1) {
        return false;
    }

    @Override
    public boolean canConvert(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        return false;
    }

    @Override
    public <T> T convert(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public Object convert(Object o, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        return null;
    }
}
