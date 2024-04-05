package main;

import java.util.ArrayList;

public class SysMLComponents {
    ArrayList<Block> blocks;
    ArrayList<Port> ports;
    ArrayList<Property> properties;
    ArrayList<PortProperty> portProperties;
    public SysMLComponents(){
        blocks = new ArrayList<Block>();
        ports = new ArrayList<Port>();
        properties = new ArrayList<Property>();
        portProperties = new ArrayList<PortProperty>();
    }
    public void createBlock(String name, String xmiID) {
        blocks.add(new Block(name,xmiID));
    }
    public void createProperties(String name, String xmiID, String ownerXMI, String reusePropertyID) {
        properties.add(new Property(name,xmiID, ownerXMI, reusePropertyID));
    }
    public void createPorts(String name, String xmiID, String ownerXMI) {
        ports.add(new Port(name, xmiID, ownerXMI));
    }
    }

    public void setPortProperty(String name, String XmiID, String ownerXMI, String reuseProperty) {
        portProperties.add(new PortProperty(name,XmiID,ownerXMI,reuseProperty));
    }
    public String getXMI(String owner){
        for(Block block: blocks){
            if(block.name.equals(owner)){
                return block.XmiID;
            }
        }
//    public Block getBlockByType(String type){
//        for(Block block: components){
//            if(block.type.equals(type)){
//                return block;
//            }
//        }
//        return null;
//    }

    public Block getBlockByName(String name){
        for(Block block: blocks){
            if(block.name.equals(name)){
                return block;
            }
        }
        return null;
    }
//    public Block getBlockByNameOwner(String name, String ownerXMI){
//        for(Block block: components){
//            if(block.name.equals(name) && block.ownerXMI.equals(ownerXMI)){
//                return block;
//            }
//        }
//        return null;
//    }
//    public Block getBlockByNameType(String name, String type){
//        for(Block block: components){
//            if(block.name.equals(name)&& block.type.equals(type)){
//                return block;
//            }
//        }
//        return null;
//    }

//        return null;
//    }
//    public String getXMIByType(String owner, String type){
//        for(Block block: components){
//            if(block.name.equals(owner) && block.type.equals(type)){
//                return block.XmiID;
//            }
//        }
//        return null;
//    }
    public String getNameByXMI(String XMI){
        for(Block block: blocks){
            if(block.XmiID.equals(XMI)){
                return block.name;
            }
        }
        return null;
    }
//    public String getOwnerByOwnerXMI(String XMI){
//        for(Block block: components){
//            if(block.ownerXMI.equals(XMI)){
//                return block.name;
//            }
//        }
//        return null;
//    }
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
    public PortProperty getPortPropertyBlockXMI(String port, String XMI) {
        for(PortProperty portProperty: portProperties){
            if(portProperty.name.equals(port) && portProperty.XmiID.equals(XMI)){
                return portProperty;
            }
        }
        return null;
    }
    public PortProperty getPortPropertyByOwnerXMI(String XMI) {
        for(PortProperty portProperty: portProperties){
            if(portProperty.ownerXMI.equals(XMI)){
                return portProperty;
            }
        }
        return null;
    }

//    public String getXMIbyType(String type, String structNoun) {
//        for (Block block : components) {
//            if (block.name.equals(structNoun) && block.type.equals(type)) {
//                return block.XmiID;
//            }
//        }
//        return null;
//    }
}


