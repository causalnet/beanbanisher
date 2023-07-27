# Spring Boot Bean Banisher

Disable Spring beans by name or type with a system property.

Can be used to give the ability to disable arbitrary beans of an application via command line arguments.

| Property                    | Description                                                                           |
|-----------------------------|---------------------------------------------------------------------------------------|
| beanBanisher.beanNames      | A list of bean names (comma separated) to disable                                     |
| beanBanisher.beanClassNames | A list of fully qualified class names, any bean of exactly this type will be disabled |

## Example

Once Bean Banisher is built as part of a Spring Boot application, the following command line argument:

```
-DbeanBanisher.beanNames=someService
```

will disable the `someService` bean.

## Why?

There are usually better ways to disable beans via configuration (`@ConditionalOnProperty`, etc.) 
if you are in control of that configuration.  For the rare cases where a bean has been introduced from 
a configuration not under your control, Bean Banisher can be used.

## Using with an already-built Spring application JAR

For situations where you need to use Bean Banisher with a pre-built Spring Boot application, instead of 
starting the application with `java -jar myapplication.jar`, do this:

- Put the Spring Boot application JAR into a directory, such as `app`
- Add the Bean Banisher JAR to this directory

and use command line arguments after `java`:

- `-Dloader.path=app`
- `-DbeanBanisher.beanNames=<whatever beans you want to disable>`
- `-cp app/myapplication.jar`
- `org.springframework.boot.loader.PropertiesLauncher`
- ... any extra arguments
