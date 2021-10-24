package com.ss.meet.meetapi.application;

import java.io.Serializable;
import java.util.List;

import com.ss.meet.meetapi.domain.AbstractEntity;

public interface AbstractSearchApplication <T extends AbstractEntity, I extends Serializable>{
    T findById(I id);
    List<T> all();
}
