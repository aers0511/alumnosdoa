package com.alumnos.daoalumnos.utils.helpers;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alumnos.daoalumnos.common.Identificable;

public abstract class PathHelper {

    public static URI locationHelperID(Identificable o, String path) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(path)
                .buildAndExpand(o.getId())
                .toUri();
    }

}
