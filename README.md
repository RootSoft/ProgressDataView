# ProgressDataView

[ ![Download](https://api.bintray.com/packages/tomasverhelst/ProgressDataView/progressdataview/images/download.svg) ](https://bintray.com/tomasverhelst/ProgressDataView/progressdataview/_latestVersion)

ProgressDataView is a view to easily switch between loading, empty, data and error states in your Android Application.

- 4 different states (loading, empty, error and data)
- Extends from Framelayout
- Add child views as default content
- Pass different state layout files in XML

## Gradle
---

```
compile 'com.rootsoft.progressdataview:progressdataview:1.0.2'
```

## Usage

### Java
---

```java
@BindView(R.id.progress_airports)
public ProgressDataView progressAirport;
```

```java
public void fetchVisitedAirports(boolean showLoading) {
    if (showLoading)
        progressAirport.showLoading();

    airportManager.getVisitedAirports(new AirportManager.VisitedAirportsCallback() {

        @Override
        public void onVisitedAirportsReceived(List<Airport> airports) {
            if (airports.size() == 0) {
                progressAirport.showEmpty();
                return;
            }
            progressAirport.showContent();
        }

        @Override
        public void onError(String message) {
            progressAirport.showError();
        }
    });
}
```

### XML
---

```xml
<com.rootsoft.progressdataview.ProgressDataView
    android:id="@+id/progress_airports"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:loadingLayout="@layout/layout_default_loading"
    app:errorLayout="@layout/layout_default_error"
    app:emptyLayout="@layout/layout_default_empty">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/list_airports"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:overScrollMode="never">

    </android.support.v7.widget.RecyclerView>

</com.rootsoft.progressdataview.ProgressDataView>
```




