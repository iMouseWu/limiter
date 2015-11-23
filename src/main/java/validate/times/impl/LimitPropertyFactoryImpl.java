package validate.times.impl;

import validate.times.LimitProperty;
import validate.times.LimitPropertyFactory;

public class LimitPropertyFactoryImpl implements LimitPropertyFactory {

	private LimitProperty limitProperty;

	@Override
	public LimitProperty getProperty(String vistor, String resource) {
		return limitProperty;
	}

}
