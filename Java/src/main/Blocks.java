package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
public class Blocks {
    ArrayList<Block> blocks;

    public Blocks(){
        blocks = new ArrayList<Block>();
    }
    public void createBlock(String type, String name, String XmiID) {
        blocks.add(new Block(type,name,XmiID));
    }
    public void createBlockWithOwner(String type, String name, String XmiID, String ownerXMI) {
        blocks.add(new Block(type,name,XmiID, ownerXMI));
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
}
