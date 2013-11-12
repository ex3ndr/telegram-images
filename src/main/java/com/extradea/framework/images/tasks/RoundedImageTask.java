/*
 * Copyright (c) 2013 Extradea LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.extradea.framework.images.tasks;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ex3ndr
 * Date: 11.06.12
 * Time: 23:44
 */
public class RoundedImageTask extends ImageTask implements Serializable {
    private String srcKey;

    private int radius = 6;

    public RoundedImageTask(String srcKey) {
        this.srcKey = srcKey;

        ImageDownloadTask[] requiredTasks = new ImageDownloadTask[]{
                new ImageDownloadTask(srcKey)
        };
        for (ImageTask task : requiredTasks) {
            task.setPutInMemoryCache(false);
        }

        setRequiredTasks(requiredTasks);
    }

    public RoundedImageTask(ImageTask task) {
        task.setPutInMemoryCache(false);
        setRequiredTasks(new ImageTask[]{task});
        srcKey = task.getKey();
    }

    @Override
    public boolean skipDiskCacheCheck() {
        return true;
    }

    @Override
    public String getKeyImpl() {
        return "Rounded(" + radius + "):" + srcKey;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}