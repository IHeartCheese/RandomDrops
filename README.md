# RandomDrops

A Bukkit plugin for Minecraft that makes blocks drop a random when broken.

### Usage

/activate <block/total>\
/deactivate

##### Block

Block mode means that one block will **always** drop the same other item, for example breaking dirt would always drop flower pots.

##### Total

Total mode means that any broken block will drop a random item. Unlike block mode, breaking 5 block of dirt will not result in 5 of the same item.\
Breaking grass and tall grass drops nothing.

### Bugs

Deactivating and reactivating the plugin causes multiple items to drop as onBlockBreak event seems to run multiple times. can be fixed by reloading the plugin.
