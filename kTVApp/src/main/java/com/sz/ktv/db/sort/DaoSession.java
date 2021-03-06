package com.sz.ktv.db.sort;

import java.util.Map;

import android.database.sqlite.SQLiteDatabase;

import com.sz.ktv.db.Song;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig personDaoConfig;

    private final SongSortDao personDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        personDaoConfig = daoConfigMap.get(SongSortDao.class).clone();
        personDaoConfig.initIdentityScope(type);

        personDao = new SongSortDao(personDaoConfig, this);

        registerDao(Song.class, personDao);
    }
    
    public void clear() {
        personDaoConfig.getIdentityScope().clear();
    }

    public SongSortDao getPersonDao() {
        return personDao;
    }

}
