package wf.apiasistencias.appasistencias.response;

public class RespuestaGenerica<T> {
    private boolean success;
    private T results;

    public RespuestaGenerica(boolean success, T results) {
        this.success = success;
        this.results = results;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResults() {
        return results;
    }
}

