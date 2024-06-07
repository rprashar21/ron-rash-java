Spring annotations
@SpringBootApplication -- combination of  @enableAutoconfiguration,componetScan,configuration

enableAutoconfiguration-  auto configures the spring application and all the dependencies we have in the classpath and creates
beans for that -- looks in the pom.xml -- for eg if we have h2 database jar it will configure all the beans related totto that

ComponentScan -- it will scan the beans and will make it visible to all the Ioc conatiner
we can also provide the base packages and classes to load specific classes to the ioc container

@Configuration -- used for java based configuration -- we have bean definitions inside this class and whena componetScan
is scan all the beans and provide it to the ioc conatiner..

Sterotype Annotations? -- spring will see these annotaions and manage the lifecycle of these beans
@Component -
@Service
@Repository
@RestController

@PostConstruct -- when the spring context has finished creating the beans -- ie Sterotype annotations-(we have no control over)
and we want to execute some instructions we can use this annotation
Like connecting to the db or initializing some data.

@PreDestroy-- Just before destroying all the beans by spring ,this method will be called - eg closing db operations

Spring Core related annotaion
@Configuration and @Bean-- to manage the java based beans/configuration-- we dont want to manage the lifecycle of these beans uig xml or annotations
it is made availabe to the ioc container when it sees it will create the bean and maintain the lifecylce for it -- usually when we use kafka or restTemplate

@Qualifier -- tells ioc or application context / spring which bean to load when there are multiple implementations of a service
@Primary -- annotate the bean which u want to give priority and annotate It with this
@Lazy -- lazing loading of the Beans
@Value -- to load any value from the properties file @Value("${mail.from}")
@PropertySource


@Profile -- load configuration specific to an environment.

@Configuration
public class config{

@Bean
@Profile("stg")  -- filename should be application-stg.properties
public String dataSourceProps()
{

}
}

@Scope - by default singleton --
prototye -- for every rest call
session -- bean for every
request

@RequestBody -- for post when we pass the entire payload and map to our model
@PathVariable-- we have to pass the var in the url else we will get 404 USERSGROUPS_SYSTEM_USER_ID

@GetMapping("/{id}")
@SneakyThrows
public ResponseEntity<Optional<Student>> getStudent(@PathVariable int id )
{

}

@RequestParam -- we can pass   --- url http://localhost:8080/students/bySirName?id=100
@getMapping("/bySirName")
public ResponseEntity<Optional<Student>> getStudent(@RequestParam("id") int id )
{

}

@ControllerAdvice 0r RestControllerAdvice--
When we get an exception spring will look for the above annotations to handle the exceptions-- inside this class we will
look for the exception and

@RestControllerAdvice
public class  ApplicationException{

@ExceptionHandler(StudentException.class)
public ResponseEntity<Error> handleException(Srt ex)
{

}
}
