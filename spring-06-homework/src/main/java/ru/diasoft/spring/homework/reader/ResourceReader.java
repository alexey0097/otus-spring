package ru.diasoft.spring.homework.reader;

public interface ResourceReader<F> {

    F read(String resource);

}
