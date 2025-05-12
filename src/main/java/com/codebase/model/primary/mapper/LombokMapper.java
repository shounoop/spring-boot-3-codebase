package com.codebase.model.primary.mapper;

import com.codebase.model.primary.dto.LombokDestination;
import com.codebase.model.primary.dto.SimpleDestination;
import com.codebase.model.primary.dto.SimpleSource;
import org.mapstruct.Mapper;

@Mapper
public interface LombokMapper {
    SimpleDestination sourceToDestination(SimpleSource source);
    LombokDestination sourceToLombokDestination(SimpleSource source);
}