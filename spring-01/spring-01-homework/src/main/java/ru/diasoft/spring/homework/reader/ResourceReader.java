package ru.diasoft.spring.homework.reader;

import org.springframework.core.io.Resource;

public interface ResourceReader<F> {

    F read(Resource resource);

}
