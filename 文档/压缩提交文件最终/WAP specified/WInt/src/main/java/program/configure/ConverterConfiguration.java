package program.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import program.bean.StringAndNumber;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class ConverterConfiguration {
    @Resource(name = "defaultConversionService")
    private GenericConversionService genericConversionService;
    @PostConstruct
    public void init(){
        genericConversionService.addConverter(new Converter<String, StringAndNumber>() {
            @Override
            public StringAndNumber convert(String s) {
                return new StringAndNumber();
            }
        });
    }
    @Bean
    public Converter<Object[],StringAndNumber> string2DateAndCountConverter1 (){
        Converter<Object[], StringAndNumber> converter = new Converter<Object[], StringAndNumber>() {
            @Override
            public StringAndNumber convert(Object[] s) {
                return new StringAndNumber();
            }
        };
        genericConversionService.addConverter(converter);
        return converter;
    }
    @Bean
    public Converter<String ,StringAndNumber> string2DateAndCountConverter2 (){
        Converter<String, StringAndNumber> converter2 = new Converter<String, StringAndNumber>() {
            @Override
            public StringAndNumber convert(String s) {
                return new StringAndNumber();
            }
        };
        genericConversionService.addConverter(converter2);
        return converter2;
    }
}
