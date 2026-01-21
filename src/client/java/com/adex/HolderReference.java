package com.adex;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class HolderReference<T> extends Holder.Reference<T> {

    public HolderReference( @Nullable ResourceKey<T> resourceKey, @Nullable T object) {
        super(Type.STAND_ALONE, null, resourceKey, object);
    }

    @Override
    public boolean canSerializeIn(@NonNull HolderOwner<T> holderOwner) {
        return true;
    }
}
