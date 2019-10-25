package grape;

public final class Grape {
    private static class GrapeException extends RuntimeException
    {   
        private static final long serialVersionUID = 1L;

        public GrapeException(String message)
        {
            super("Grape Error -> " + message);
        }

        public void printStackTrace()
        {
            super.printStackTrace();
        }
    }

    static public final class ConfigurationException extends GrapeException
    {
        private static final long serialVersionUID = 1L;

        public ConfigurationException(String message)
        {
            super("Configuration -> " + message);
        }
    }
}