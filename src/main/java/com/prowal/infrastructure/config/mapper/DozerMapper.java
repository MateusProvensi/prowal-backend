package com.prowal.infrastructure.config.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	private static Mapper mapper = DozerBeanMapperBuilder.create().build();

	public static <O, D> D parseObject(O origin, Class<D> destination) {
		if (origin == null)
			return null;

		return mapper.map(origin, destination);
	}

	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();

		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}

		return destinationObjects;
	}
}
