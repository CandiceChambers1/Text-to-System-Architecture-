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

    public void createBlock(String name, String xmiID) {
        blocks.add(new Block(name, xmiID));
    }

    public void createPorts(String name, String xmiID, String ownerXMI) {
        ports.add(new Port(name, xmiID, ownerXMI));
    }

    public void createProperties(String name, String xmiID, String ownerXMI, String propertyType) {
        properties.add(new Property(name, xmiID, ownerXMI, propertyType));
    }
    public  void createPortProperties(String name, String xmiID, String ownerXMI, String reuseProperty){
        portProperties.add(new PortProperty(name, xmiID, ownerXMI, reuseProperty));
    }

    public String getXMI(String owner) {
        for (Block block : blocks) {
            if (block.name.equals(owner)) {
                return block.XmiID;
            }
        }
        return null;
    }

    public void setPortProperty(String name, String XmiID, String ownerXMI, String reuseProperty) {
        portProperties.add(new PortProperty(name, XmiID, ownerXMI, reuseProperty));
    }
}
