package nl.tvdven.ligatum;


import nl.tvdven.ligatum.cli.Cli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements ApplicationRunner {


    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Cli cli;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
//        logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
//        logger.info("OptionNames: {}", args.getOptionNames());

//        for (String name : args.getOptionNames()){
//            logger.info("arg-" + name + "=" + args.getOptionValues(name));
//        }
        cli.parse(args.getSourceArgs());

    }


}
