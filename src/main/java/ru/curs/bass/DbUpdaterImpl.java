package ru.curs.bass;

import ru.curs.celesta.CelestaException;
import ru.curs.celesta.ConnectionPool;
import ru.curs.celesta.dbutils.DbUpdater;
import ru.curs.celesta.dbutils.adaptors.DBAdaptor;
import ru.curs.celesta.score.Grain;

public class DbUpdaterImpl extends DbUpdater<CallContext> {

    public DbUpdaterImpl(ConnectionPool connectionPool, Score score,
                         boolean forceDdInitialize, DBAdaptor dbAdaptor) {
        super(connectionPool, score, forceDdInitialize, dbAdaptor);
    }

    @Override
    protected void processGrainMeta(Grain grain) throws CelestaException {

    }

    @Override
    protected CallContext createContext() throws CelestaException {
        return new CallContext(dbAdaptor, connectionPool.get(), (Score) score);
    }

    @Override
    protected void initDataAccessors(CallContext callContext) throws CelestaException {
        schemaCursor = new SchemaDataAccessor(callContext);
    }

    @Override
    protected String getSchemasTableName() {
        return SchemaDataAccessor.TABLE_NAME;
    }
}
