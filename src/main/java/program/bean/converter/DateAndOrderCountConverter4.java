package program.bean.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import program.bean.StringAndNumber;

//@Component
public class DateAndOrderCountConverter4 implements Converter<Object,StringAndNumber> {
//    @Resource
    GenericConversionService defaultConversionService;
    public DateAndOrderCountConverter4(){
        System.out.println("init converter");
    }
    @Override
    public StringAndNumber convert(Object s) {
        return new StringAndNumber();
    }
//    @PostConstruct
    public void init(){
        defaultConversionService.addConverter(this);
    }
}
