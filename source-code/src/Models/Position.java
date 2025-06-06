/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author ThanhNhan
 */
public class Position {
    private String positionID;
    private String positionName;
    
    public Position (String id, String name) {
        this.positionID = id;
        this.positionName = name;
    }
            
    public String getID() {return this.positionID; };
    public String getName() {return this.positionName; };
}
