/*
 * Copyright 2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Amazon Software License (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.snowplowanalytics.snowplow.kinesis.redshiftmanifest;

import com.amazonaws.services.kinesis.connectors.KinesisConnectorConfiguration;
import com.amazonaws.services.kinesis.connectors.impl.AllPassFilter;
import com.amazonaws.services.kinesis.connectors.impl.BasicMemoryBuffer;
import com.amazonaws.services.kinesis.connectors.impl.StringToByteArrayTransformer;
import com.amazonaws.services.kinesis.connectors.interfaces.IBuffer;
import com.amazonaws.services.kinesis.connectors.interfaces.IEmitter;
import com.amazonaws.services.kinesis.connectors.interfaces.IFilter;
import com.amazonaws.services.kinesis.connectors.interfaces.IKinesisConnectorPipeline;
import com.amazonaws.services.kinesis.connectors.interfaces.ITransformer;
import com.amazonaws.services.kinesis.connectors.s3.S3ManifestEmitter;

/**
 * The Pipeline used by the {@link S3ManifestExecutor} in the Redshift manifest sample.
 * Outputs S3 file name records in String format. Uses:
 * <ul>
 * <li>{@link S3ManifestEmitter}</li>
 * <li>{@link BasicMemoryBuffer}</li>
 * <li>{@link StringToByteArrayTransformer}</li>
 * <li>{@link AllPassFilter}</li>
 * </ul>
 */
public class S3ManifestPipeline implements IKinesisConnectorPipeline<String, byte[]> {

    @Override
    public IEmitter<byte[]> getEmitter(KinesisConnectorConfiguration configuration) {
        return new S3ManifestEmitter(configuration);
    }

    @Override
    public IBuffer<String> getBuffer(KinesisConnectorConfiguration configuration) {
        return new BasicMemoryBuffer<String>(configuration);
    }

    @Override
    public ITransformer<String, byte[]> getTransformer(KinesisConnectorConfiguration configuration) {
        return new StringToByteArrayTransformer();
    }

    @Override
    public IFilter<String> getFilter(KinesisConnectorConfiguration configuration) {
        return new AllPassFilter<String>();
    }

}
