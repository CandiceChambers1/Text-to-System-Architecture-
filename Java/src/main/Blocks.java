package main;

import java.util.ArrayList;

public class Blocks {
    ArrayList<Block> blocks;
    ArrayList<PortProperty> portProperties;
    public Blocks(){
        blocks = new ArrayList<Block>();
        portProperties = new ArrayList<PortProperty>();
    }
    public void createBlock(String type, String name, String XmiID) {
        blocks.add(new Block(type,name,XmiID));
    }
    public void createBlockWithOwner(String type, String name, String XmiID, String ownerXMI) {
        blocks.add(new Block(type,name,XmiID, ownerXMI));
    }
    public void setPortProperty(String name, String XmiID, String ownerXMI, String reuseProperty) {
        portProperties.add(new PortProperty(name,XmiID,ownerXMI,reuseProperty));
    }
    public Block getBlockByType(String type){
        for(Block block: blocks){
            if(block.type.equals(type)){
                return block;
            }
        }
        return null;
    }
    public Block getBlockByName(String name){
        for(Block block: blocks){
            if(block.name.equals(name)){
                return block;
            }
        }
        return null;
    }
    public String getXMI(String owner){
        for(Block block: blocks){
            if(block.name.equals(owner)){
                return block.XmiID;
            }
        }
        return null;
    }

    public String getNameByXMI(String XMI){
        for(Block block: blocks){
            if(block.XmiID.equals(XMI)){
                return block.name;
            }
        }
        return null;
    }
    public String getPortPropertybyName(String name) {
        for(PortProperty portProperty: portProperties){
            if(portProperty.name.equals(name)){
                return portProperty.name;
            }
        }
        return null;
    }
    public PortProperty getPortProperty(String port) {
        for(PortProperty portProperty: portProperties){
            if(portProperty.name.equals(port)){
                return portProperty;
            }
        }
        return null;
    }
    public PortProperty getPortProperty(String port, String XMI) {
        for(PortProperty portProperty: portProperties){
            if(portProperty.name.equals(port) && portProperty.ownerXMI.equals(XMI)){
                return portProperty;
            }
        }
        return null;
    }

    public String getXMIbyType(String type, String structNoun) {
        for (Block block : blocks) {
            if (block.name.equals(structNoun) && block.type.equals(type)) {
                return block.XmiID;
            }
        }
        return null;
    }
}
