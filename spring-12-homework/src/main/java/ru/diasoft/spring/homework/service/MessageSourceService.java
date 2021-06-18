package ru.diasoft.spring.homework.service;

public interface MessageSourceService {
    String getMessage(String prop);
    String getMessage(String prop, Object... objects);
}
