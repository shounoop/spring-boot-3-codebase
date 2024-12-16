package com.codebase.model.mapper;

import com.codebase.model.dto.LombokDestination;
import com.codebase.model.dto.SimpleDestination;
import com.codebase.model.dto.SimpleSource;
import org.mapstruct.Mapper;

@Mapper
public interface LombokMapper {
    SimpleDestination sourceToDestination(SimpleSource source);
    LombokDestination sourceToLombokDestination(SimpleSource source);
}