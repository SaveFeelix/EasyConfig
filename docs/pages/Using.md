# Using
<hr />

- [Basic](#basics)
- [Initialize](#initialize)

<hr />
<hr />

### Basics
With this API you can create/manage Configs
<details>
<summary>Code Example</summary>

```java
import de.feelix.easyconfig.EasyConfig;
import org.jetbrains.annotations.NotNull;

public class MyConfig extends EasyConfig {

    public MyConfig(@NotNull String fileName) {
        super(fileName);
    }

    public MyConfig(@NotNull String folderName, @NotNull String fileName) {
        super(folderName, fileName);
    }

    @Override
    public void addDefault() {
        this.add("test1", "TestString");
        this.add("test2", 2);
    }
    
    public String getTest1() {
        return this.getFromConfig("test1");
    }
    public Integer getTest2() {
        return this.getFromConfig("test2");
    }
}
```
</details>
<hr />
<hr />

### Initialize
You can initialize in your MainClass
<details>
<summary>Code Example</summary>

```java
import de.feelix.easyconfig.EasyConfig;
import org.jetbrains.annotations.NotNull;

public class TestClass {
    public static void main(String[] args) {
        MyConfig config = new MyConfig();
        System.out.println(config.getTest1());
        System.out.println(config.getTest2());
    }
}

public class MyConfig extends EasyConfig {
    public MyConfig() {
        super("test.yml");
    }

    @Override
    public void addDefault() {
        this.add("test1", "TestString");
        this.add("test2", 2);
    }

    public String getTest1() {
        return this.getFromConfig("test1");
    }
    public Integer getTest2() {
        return this.getFromConfig("test2");
    }
}
```
</details>
<hr />
<hr />