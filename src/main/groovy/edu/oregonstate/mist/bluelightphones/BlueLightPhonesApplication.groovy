package edu.oregonstate.mist.bluelightphones

import edu.oregonstate.mist.api.Application
import edu.oregonstate.mist.bluelightphones.db.BlueLightDAO
import edu.oregonstate.mist.bluelightphones.resources.BlueLightResource
import io.dropwizard.setup.Environment

/**
 * Main application class.
 */
class BlueLightPhonesApplication extends Application<BlueLightConfiguration> {
    /**
     * Parses command-line arguments and runs the application.
     *
     * @param configuration
     * @param environment
     */
    @Override
    public void run(BlueLightConfiguration configuration, Environment environment) {
        this.setup(configuration, environment)

        final BlueLightDAO BLUELIGHTDAO = new BlueLightDAO(configuration.blueLightCoordinates)
        environment.jersey().register(new BlueLightResource(BLUELIGHTDAO))
    }

    /**
     * Instantiates the application class with command-line arguments.
     *
     * @param arguments
     * @throws Exception
     */
    public static void main(String[] arguments) throws Exception {
        new BlueLightPhonesApplication().run(arguments)
    }
}
