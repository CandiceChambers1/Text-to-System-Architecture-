package main;

import java.util.ArrayList;

public class SysMLComponent {
    ArrayList<Block> blocks;
    ArrayList<Port> ports;
    ArrayList<Property> properties;
    ArrayList<PortProperty> portProperties;

    public SysMLComponent() {
        blocks = new ArrayList<Block>();
        ports = new ArrayList<Port>();
        properties = new ArrayList<Property>();
        portProperties = new ArrayList<PortProperty>();
    }

    // Create a block
    public void createBlock(String name, String xmiID) {
        blocks.add(new Block(name, xmiID));
    }

    // Create a Port
    public void createPorts(String name, String xmiID, String ownerXMI) {
        ports.add(new Port(name, xmiID, ownerXMI));
    }

    // Create a Property
    public void createProperties(String name, String xmiID, String ownerName, String ownerXMI, String propertyType) {
        properties.add(new Property(name, xmiID, ownerName, ownerXMI, propertyType));
    }

    // Create Port Properties
    public  void createPortProperties(String name, String xmiID, String ownerName, String ownerXMI, String reuseProperty){
        portProperties.add(new PortProperty(name, xmiID, ownerName, ownerXMI, reuseProperty));
    }

    // Get a Block's XMI based on its name
    // Assumption: Block names are unique
    public String getBlockXMI(String name) {
        for (Block block : blocks) {
            if (block.name.equals(name)) {
                return block.xmiID;
            }
        }
        return null;
    }

    // Get Property Owner XMI based on the ownerName
    // Assumption: Owner Names are unique (Blocks)
    public String getPropertyOwnerXMI(String ownerName){
        for(Property property: properties){
            if(property.ownerName.equals(ownerName)){
                return property.ownerXMI;
            }
        }
        return null;
    }
    // Get Property XMI based on the name and owner
    public String getPropertyXMI(String name) {
        for (Property property : properties) {
//            System.out.println(property.name);
            if (property.name.equals(name)) {
                return property.xmiID;
            }
        }
        return null;
    }
    public String getPortXMI(String name) {
        for (Port port : ports) {
            if (port.name.equals(name)) {
                return port.xmiID;
            }
        }
        return null;
    }
    public PortProperty getPortProperty(String name, String ownerXMI) {
        for (PortProperty portProperty : portProperties) {
            if (portProperty.name.equals(name) && portProperty.ownerXMI.equals(ownerXMI)) {
                return portProperty;
            }
        }
        return null;
    }

    public Property getProperty(String name, String ownerName) {
        for (Property property : properties) {
            if (property.name.equals(name) && property.ownerName.equals(ownerName)) {
                return property;
            }
        }
        return null;
    }

    public Property getPropertyByXMI(String xmiID) {
        for (Property property : properties) {
            if (property.xmiID.equals(xmiID)) {
                return property;
            }
        }
        return null;
    }

    public String getPropertyXMIbyOwner(String name, String ownerXMI) {
        for (Property property : properties) {
            if (property.name.equals(name) && property.ownerXMI.equals(ownerXMI)) {
                return property.xmiID;
            }
        }
        return null;
    }
}
