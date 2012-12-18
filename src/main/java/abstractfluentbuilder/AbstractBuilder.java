package abstractfluentbuilder;


public abstract class AbstractBuilder<T, B> {

	public static final String ACCESS_TARGET_OBJECT_METHOD_NAME = "targetObject";
	public static final String ACCESS_BUILDER_METHOD_NAME = "builder";
	public static final String BUILD_METHOD_NAME = "build";


	protected abstract T targetObject();

	protected abstract B builder();

	public abstract T build();
}
