/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.search.store;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.search.indexes.spi.DirectoryBasedIndexManager;
import org.hibernate.search.spi.BuildContext;
import org.hibernate.search.store.DirectoryProvider;
import org.hibernate.search.store.spi.DirectoryHelper;
import org.lumongo.storage.lucene.DistributedDirectory;
import org.lumongo.storage.lucene.MongoDirectory;

/**
 *
 * @author maykoone
 */
public class MongoDbDirectoryProvider implements DirectoryProvider<DistributedDirectory> {

    public static final String DBNAME = "lucene";
    private DistributedDirectory directory;
    private String indexName;
    private Properties properties;

    @Override
    public void initialize(String indexName, Properties properties, BuildContext bc) {
        Logger.getLogger(MongoDbDirectoryProvider.class.getName()).log(Level.INFO, "SEARCH: initialize directory for index {0}", indexName);
        this.indexName = indexName;
        this.properties = properties;
        directory = createDirectory();
    }

    @Override
    public void start(DirectoryBasedIndexManager dbim) {
        DirectoryHelper.initializeIndexIfNeeded(directory);
//        DirectoryProviderHelper.initializeIndexIfNeeded(directory);
    }

    @Override
    public void stop() {
        try {
            directory.close();
        } catch (IOException ex) {
            Logger.getLogger(MongoDbDirectoryProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DistributedDirectory getDirectory() {
        return directory;
    }

    protected DistributedDirectory createDirectory() {
        DistributedDirectory d = null;
        try {
            MongoClientURI uri = new MongoClientURI(System.getenv("MONGOHQ_URL"));
            MongoClient mongo = new MongoClient(uri);
            d = new DistributedDirectory(new MongoDirectory(mongo, uri.getDatabase(), indexName));
        } catch (IOException ex) {
            Logger.getLogger(MongoDbDirectoryProvider.class.getName()).log(Level.SEVERE, "Unable to create directory abstraction", ex);
        } catch (Exception ex) {
            Logger.getLogger(MongoDbDirectoryProvider.class.getName()).log(Level.SEVERE, "Unable to create directory abstraction", ex);
        }
        return d;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.indexName != null ? this.indexName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MongoDbDirectoryProvider other = (MongoDbDirectoryProvider) obj;
        if ((this.indexName == null) ? (other.indexName != null) : !this.indexName.equals(other.indexName)) {
            return false;
        }
        return true;
    }
}
