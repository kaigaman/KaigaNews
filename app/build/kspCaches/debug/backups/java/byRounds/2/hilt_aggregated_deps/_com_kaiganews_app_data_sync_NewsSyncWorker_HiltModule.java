package hilt_aggregated_deps;

import dagger.hilt.processor.internal.aggregateddeps.AggregatedDeps;
import javax.annotation.processing.Generated;

/**
 * This class should only be referenced by generated code! This class aggregates information across multiple compilations.
 */
@AggregatedDeps(
    components = "dagger.hilt.components.SingletonComponent",
    modules = "com.kaiganews.app.data.sync.NewsSyncWorker_HiltModule"
)
@Generated("dagger.hilt.processor.internal.aggregateddeps.AggregatedDepsGenerator")
public class _com_kaiganews_app_data_sync_NewsSyncWorker_HiltModule {
}
