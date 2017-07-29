package program.bean.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import program.bean.StringAndNumber;

//@Component
public class DateAndOrderCountConverter3 implements Converter<String[],StringAndNumber> {
//    @Resource
    GenericConversionService defaultConversionService;
    public DateAndOrderCountConverter3(){
        System.out.println("init converter");
    }
    @Override
    public StringAndNumber convert(String[] s) {
        return new StringAndNumber();
    }
//    @PostConstruct
    public void init(){
        defaultConversionService.addConverter(this);
    }
}
