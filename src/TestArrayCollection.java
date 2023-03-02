
public class TestArrayCollection extends TestCollection<String> {
	@Override
	protected void initCollections() {
		e = new String[] { null, "apples", "bread", "celery", "donuts", "eggs", "fish", "graps", "hello", "ice", "jello", "kale" };
		c = new ArrayCollection<String>();
	}

}
