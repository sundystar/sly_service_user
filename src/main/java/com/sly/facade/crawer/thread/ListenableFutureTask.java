package com.sly.facade.crawer.thread;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.sly.facade.crawer.model.SightHotPhoto;

public class ListenableFutureTask implements Future<List<SightHotPhoto>> {

	public boolean cancel(boolean mayInterruptIfRunning) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<SightHotPhoto> get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SightHotPhoto> get(long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

}
