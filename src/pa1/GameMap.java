package pa1;


import pa1.ministers.Economist;
import pa1.ministers.Minister;
import pa1.ministers.Scientist;
import pa1.ministers.WarGeneral;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds the necessary components for running the game
 */
public class GameMap {
    private List<Player> players;
    private Map<Integer, Cell> cityLocations;
    private Map<Integer, List<Integer>> connectedCities;
    private int width, height;

    /**
     * Load the map from a text file
     * The outline of the map format is given in the assignment description
     *
     * You should instantiate cityLocations and connectedCities here
     *
     * @param filename
     * @throws IOException
     */
    public void loadMap(String filename) throws IOException {
        // TODO
        File inputFile = new File(filename);
        cityLocations = new HashMap<>();
        connectedCities = new HashMap<>();
        try(
            Scanner reader = new Scanner(inputFile);
        ){
            width = Integer.valueOf(reader.nextLine());
            height = Integer.valueOf(reader.nextLine());
            String numCities = reader.nextLine();

            reader.nextLine();

            for(int i=0; i < Integer.valueOf(numCities); i++){
                String cityId = reader.next();
                String coordinates = reader.next();

                String[] coordinatesSplit = coordinates.split(",");
                String cityXCoordinates = coordinatesSplit[0];
                String cityYCoordinates = coordinatesSplit[1];

                cityLocations.put(Integer.parseInt(cityId), new Cell(Integer.parseInt(cityXCoordinates), Integer.parseInt(cityYCoordinates)));
                reader.nextLine();
            }

            reader.nextLine();

            for(int i=0; i< Integer.parseInt(numCities); i++){
                String cityId = reader.next();
                String neighboringCities = reader.next();
                String[] neighboringCitiesSplit = neighboringCities.split(",");
                String neighboringCityId1 = neighboringCitiesSplit[0];
                String neighboringCityId2 = neighboringCitiesSplit[1];

                List<Integer> cityIDList = new ArrayList<>();

                cityIDList.add(Integer.parseInt(neighboringCityId1));
                cityIDList.add(Integer.parseInt(neighboringCityId2));

                connectedCities.put(Integer.parseInt(cityId), cityIDList);
            }
        }
    }

    /**
     * Loads player data from text file
     * The outline of the player file format is given in the assignment description
     *
     * You should instantiate the member variable players here
     *
     * @param filename
     * @throws IOException
     */
    public void loadPlayers(String filename) throws IOException {
        // TODO
        File inputFile = new File(filename);
        players = new ArrayList<>();

        try(
                Scanner reader = new Scanner(inputFile)
        ){
            String numOfPlayers = reader.nextLine();
            reader.nextLine();

            for(int i=0; i< Integer.parseInt(numOfPlayers); i++){
                String playerName = new String(reader.next());
                String gold = new String(reader.next());
                String sciencePoint = new String(reader.next());
                String productionPoint = new String(reader.next());
                String numCities = new String(reader.next());
                String numMinisters = new String(reader.next());

                reader.nextLine();

                Player newPlayer = new Player(playerName, Integer.parseInt(gold), Integer.parseInt(sciencePoint), Integer.parseInt(productionPoint));

                for(int j=0; j < Integer.parseInt(numCities); j++){
                    String id = new String(reader.next());
                    String name = new String(reader.next());
                    String population = new String(reader.next());
                    String troops = new String(reader.next());
                    String cropYields = new String(reader.next());
                    City newCity = new City(Integer.parseInt(id), name, Integer.parseInt(population), Integer.parseInt(troops), Integer.parseInt(cropYields));
                    List<City> cities = newPlayer.getCities();
                    cities.add(newCity);
                    reader.nextLine();
                }

                for(int j=0; j< Integer.parseInt(numMinisters); j++){
                    String type = new String(reader.next());
                    String intelligence = new String(reader.next());
                    String experience = new String(reader.next());
                    String leadership = new String(reader.next());
                    switch(type){
                        case "Economist":
                            Economist newEconomist = new Economist(Integer.parseInt(intelligence), Integer.parseInt(experience), Integer.parseInt(leadership));
                            newPlayer.getMinisters().add(newEconomist);
                            break;
                        case "WarGeneral":
                            WarGeneral newGeneral = new WarGeneral(Integer.parseInt(intelligence), Integer.parseInt(experience), Integer.parseInt(leadership));
                            newPlayer.getMinisters().add(newGeneral);
                            break;
                        case "Scientist":
                            Scientist newScientist = new Scientist(Integer.parseInt(intelligence), Integer.parseInt(experience), Integer.parseInt(leadership));
                            newPlayer.getMinisters().add(newScientist);
                            break;
                    }
                }

                players.add(newPlayer);
                reader.nextLine();
            }
        }
    }

    /**
     * @return list of all cities from every player
     */
    public List<City> getAllCities() {
        // TODO
        List<City> allCities = new ArrayList<>();
        for(int i=0; i<players.size(); i++){
            for(int j=0; j< players.get(i).getCities().size(); j++){
                allCities.add(players.get(i).getCities().get(j));
            }
        }

        return allCities;
    }

    /**
     * @param city
     * @return Cell object representing the city's location on the game map
     */
    public Cell getCityLocation(City city) {
        // TODO
       return cityLocations.get(city.getId());
    }

    public City getCityById(int id) {
        return getAllCities().stream()
                .filter(city -> city.getId() == id)
                .findAny()
                .orElse(null);
    }

    public Player getCityOwner(City city) {
        return getPlayers().stream()
                .filter(p -> p.hasCity(city))
                .findFirst()
                .orElse(new Player("", 0, 0, 0));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<City> getNeighboringCities(City city) {
        List<Integer> neighborIds = connectedCities.get(city.getId());
        return neighborIds.stream()
                .map(this::getCityById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void drawLine(char map[][], Cell a, Cell b) {
        if (a.getX() == b.getX()) {
            int minY = Math.min(a.getY(), b.getY());
            int maxY = Math.max(a.getY(), b.getY());

            for (int i = minY; i < maxY; i++) map[i][a.getX()] = '|';
        } else if (a.getY() == b.getY()) {
            int minX = Math.min(a.getX(), b.getX());
            int maxX = Math.max(a.getX(), b.getX());

            Arrays.fill(map[a.getY()], minX, maxX, '-');
        }
    }

    @Override
    public String toString() {
        char[][] map = new char[height][width];
        char[] vertSeparator = new char[width + 2];
        Arrays.fill(vertSeparator, '#');
        for (int i = 0; i < height; i++) Arrays.fill(map[i], ' ');

        for (int cityIdA : connectedCities.keySet()) {
            for (int cityIdB : connectedCities.get(cityIdA)) {
                Cell a = cityLocations.get(cityIdA);
                Cell b = cityLocations.get(cityIdB);
                drawLine(map, a, b);
            }
        }

        for (City city : getAllCities()) {
            Cell location = getCityLocation(city);
            int x = location.getX();
            int y = location.getY();

            char[] line = map[y];
            String cityText = city.getMapRepresentation(getCityOwner(city).getName());
            System.arraycopy(cityText.toCharArray(), 0, line, x - 3, cityText.length());
        }

        StringBuilder sb = new StringBuilder().append(vertSeparator).append('\n');
        for (char[] line : map) sb.append('#').append(line).append('#').append('\n');
        return sb.append(vertSeparator).toString();
    }


}
