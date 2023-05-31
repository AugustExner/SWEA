package hotciv.framework;

public class OperationNames {
    public static final char SEPARATOR = '-';
    //Game
    public static final String GAME_PREFIX = "game";
    public static final String GET_TILE_AT = GAME_PREFIX + SEPARATOR +"get-tile-at";
    public static final String GET_CITY_AT = GAME_PREFIX + SEPARATOR +"get-city-at";
    public static final String GET_UNIT_AT = GAME_PREFIX + SEPARATOR +"get-unit-at";
    public static final String GET_PLAYER_IN_TURN = GAME_PREFIX + SEPARATOR +"get-player-in-turn";
    public static final String GET_WINNER = GAME_PREFIX + SEPARATOR +"get-game-winner";
    public static final String AGE = GAME_PREFIX + SEPARATOR +"age";
    public static final String MOVE_UNIT = GAME_PREFIX + SEPARATOR +"move-unit";
    public static final String END_OF_TURN = GAME_PREFIX + SEPARATOR +"end-of-turn";
    public static final String CHANGE_WORKFORCE_FOCUS = GAME_PREFIX + SEPARATOR +"change-workforce-focus";
    public static final String CHANGE_PRODUCTION_FOCUS = GAME_PREFIX + SEPARATOR +"change-production-focus";

    //Unit
    public static final String UNIT_PREFIX = "unit";
    public static final String PERFORM_UNITACTION = UNIT_PREFIX + SEPARATOR +"perform-unitaction";
    public static final String GET_TYPE_STRING = UNIT_PREFIX + SEPARATOR +"get-unit-typestring";
    public static final String SET_MOVE_COUNT = UNIT_PREFIX + SEPARATOR +"unit-move-count";
    public static final String GET_OWNER = UNIT_PREFIX + SEPARATOR +"get-unit-owner";
    public static final String GET_MOVE_COUNT = UNIT_PREFIX + SEPARATOR +"get-unit-move-count";
    public static final String GET_DEFENSIVE_STRENGTH = UNIT_PREFIX + SEPARATOR +"get-unit-defensive-strength";
    public static final String GET_ATTACKING_STRENGTH = UNIT_PREFIX + SEPARATOR +"get-unit-attacking-strength";
    public static final String CHANGE_DEFENSIVE_STRENGTH= UNIT_PREFIX + SEPARATOR +"change-unit-defensive-strength";
    public static final String SET_MOVE_ABLE = UNIT_PREFIX + SEPARATOR +"set-unit-moveable";
    public static final String GET_MOVEABLE = UNIT_PREFIX + SEPARATOR +"get-unit-moveable";

    //City
    public static final String CITY_PREFIX = "city";
    public static final String GET_CITY_OWNER = CITY_PREFIX + SEPARATOR +"get-city-owner";
    public static final String GET_SIZE = CITY_PREFIX + SEPARATOR +"get-city-size";
    public static final String GET_TREASURY = CITY_PREFIX + SEPARATOR +"get-city-treasury";
    public static final String GET_PRODUCTION = CITY_PREFIX + SEPARATOR +"get-city-production";
    public static final String GET_WORKFORCEFOCUS = CITY_PREFIX + SEPARATOR +"get-city-workforcefocus";
    public static final String MINUS_TREASURY = CITY_PREFIX + SEPARATOR +"deduct-from-treasury";
    public static final String ADD_TREASURY = CITY_PREFIX + SEPARATOR +"add-to-treasury";


    //Tile
    public static final String TILE_PREFIX = "tile";
    public static final String GET_TILE_TYPE_STRING = TILE_PREFIX +  SEPARATOR + "get-tile-typestring";



}
