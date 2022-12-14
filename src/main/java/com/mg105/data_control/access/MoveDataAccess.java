package com.mg105.data_control.access;

import com.mg105.use_cases.outputds.MoveDetails;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.mg105.utils.DataAccessConstants.IS_TRUE;
import static com.mg105.utils.DataAccessConstants.MOVE_DATA_PATH;

/**
 * This is a class mean to interact and get information about moves from a "database"
 * <p>
 * This class is really only here so PartyDataAccess does not violate Single Responsibility Principle and to
 * help satisfy open and closed principle in PartyDataAccess
 */

public class MoveDataAccess {


    /**
     * Returns an object that represents the details of the move desired
     *
     * @param name the name of move to return the details of
     * @return an object that represents the details of the move desired
     * @throws NoSuchElementException iff the move's details are not found (move does not exist)
     * @see MoveDetails
     */

    public @NotNull MoveDetails getMoveDetails(String name) throws NoSuchElementException {

        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(MOVE_DATA_PATH)).withCSVParser(parser).build();
            List<String[]> partyStats = reader.readAll();

            for (String[] moveDetails : partyStats) {

                if (moveDetails[0].equals(name)) {
                    int healthChange = Integer.parseInt(moveDetails[1]);
                    int damageChange = Integer.parseInt(moveDetails[2]);
                    boolean isFriendly = moveDetails[3].equals(IS_TRUE);
                    reader.close();

                    return new MoveDetails(name, healthChange, damageChange, isFriendly);
                }

            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Could not find file");
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        throw new NoSuchElementException("No move of this name exists");

    }

}
