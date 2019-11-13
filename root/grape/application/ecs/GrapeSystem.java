package grape.application.ecs;

import grape.Grape;

public @interface GrapeSystem
{
    Class<? extends Grape.Component>[] Components();
}