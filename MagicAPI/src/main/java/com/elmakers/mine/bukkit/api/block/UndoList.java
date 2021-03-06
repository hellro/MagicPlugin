package com.elmakers.mine.bukkit.api.block;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.batch.Batch;
import com.elmakers.mine.bukkit.api.entity.EntityData;
import com.elmakers.mine.bukkit.api.spell.Spell;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import com.elmakers.mine.bukkit.api.magic.Mage;
import org.bukkit.entity.EntityType;

public interface UndoList extends BlockList, Comparable<UndoList> {
    void commit();
    void undo();
    void undo(boolean blocking);
    void undoScheduled();
    void undoScheduled(boolean blocking);
    BlockData undoNext(boolean applyPhysics);

    void setEntityUndo(boolean undoEntityEffects);
    void setEntityUndoTypes(Set<EntityType> undoTypes);

    void setScheduleUndo(int ttl);
    int getScheduledUndo();
    void updateScheduledUndo();
    boolean bypass();
    void setApplyPhysics(boolean physics);
    void setModifyType(ModifyType modifyType);
    ModifyType getModifyType();
    long getCreatedTime();
    long getModifiedTime();
    long getScheduledTime();
    boolean isScheduled();
    void setUndoSpeed(double speed);
    boolean hasBeenScheduled();
    void setHasBeenScheduled();

    void setUndoBreakable(boolean breakable);
    void setUndoReflective(boolean reflective);
    void setUndoBreaking(boolean breaking);

    void prune();

    void add(Entity entity);
    void remove(Entity entity);
    EntityData damage(Entity entity);
    EntityData modify(Entity entity);
    void add(Runnable runnable);
    void move(Entity entity);
    void modifyVelocity(Entity entity);
    void addPotionEffects(Entity entity);

    void convert(Entity entity, Block block);
    void fall(Entity entity, Block block);
    void explode(Entity entity, List<Block> explodedBlocks);
    void finalizeExplosion(Entity entity, List<Block> explodedBlocks);
    void cancelExplosion(Entity entity);
    void setBatch(Batch batch);
    void setSpell(Spell spell);
    void clearAttachables(Block block);

    boolean contains(Location location, int threshold);

    String getName();
    Mage getOwner();
    CastContext getContext();
    void setBypass(boolean bypass);
    Collection<Entity> getAllEntities();
    boolean getApplyPhysics();

    boolean isConsumed();
    void setConsumed(boolean consumed);

    /**
     * Check to see if this list has any changes that would get normally auto-undone (e.g. by scheduled undo)
     *
     * This generally only includes block changes, though if setEntityUndo(true) has been used it will include
     * entity changes as well.
     *
     * @return true if this list has changes to undo
     */
    boolean hasChanges();
}
