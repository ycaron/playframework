/*
 * Copyright (C) from 2022 The Play Framework Contributors <https://github.com/playframework>, 2011-2021 Lightbend Inc. <https://www.lightbend.com>
 */

package play.core.j

import java.io.InputStream
import java.io.File
import java.nio.file.Path
import java.util.Optional

import akka.annotation.ApiMayChange
import play.mvc.RangeResults
import play.mvc.Result

import scala.jdk.OptionConverters._
import akka.stream.javadsl.Source
import akka.util.ByteString
import play.api.mvc.RangeResult

/**
 * Java compatible RangeResult
 */
object JavaRangeResult {
  private type OptString   = Optional[String]
  private type ScalaSource = akka.stream.scaladsl.Source[ByteString, _]

  def ofStream(stream: InputStream, rangeHeader: OptString, fileName: String, contentType: OptString): Result = {
    RangeResult.ofStream(stream, rangeHeader.toScala, fileName, contentType.toScala).asJava
  }

  def ofStream(
      entityLength: Long,
      stream: InputStream,
      rangeHeader: OptString,
      fileName: String,
      contentType: OptString
  ): Result = {
    RangeResult.ofStream(entityLength, stream, rangeHeader.toScala, fileName, contentType.toScala).asJava
  }

  def ofPath(path: Path, rangeHeader: OptString, contentType: OptString): Result = {
    RangeResult.ofPath(path, rangeHeader.toScala, contentType.toScala).asJava
  }

  def ofPath(path: Path, rangeHeader: OptString, fileName: String, contentType: OptString): Result = {
    RangeResult.ofPath(path, rangeHeader.toScala, fileName, contentType.toScala).asJava
  }

  def ofFile(file: File, rangeHeader: OptString, contentType: OptString): Result = {
    RangeResult.ofFile(file, rangeHeader.toScala, contentType.toScala).asJava
  }

  def ofFile(file: File, rangeHeader: OptString, fileName: String, contentType: OptString): Result = {
    RangeResult.ofFile(file, rangeHeader.toScala, fileName, contentType.toScala).asJava
  }

  def ofSource(
      entityLength: Long,
      source: Source[ByteString, _],
      rangeHeader: OptString,
      fileName: OptString,
      contentType: OptString
  ): Result = {
    RangeResult
      .ofSource(entityLength, source.asScala, rangeHeader.toScala, fileName.toScala, contentType.toScala)
      .asJava
  }

  def ofSource(
      entityLength: Optional[Long],
      source: Source[ByteString, _],
      rangeHeader: OptString,
      fileName: OptString,
      contentType: OptString
  ): Result = {
    RangeResult
      .ofSource(entityLength.toScala, source.asScala, rangeHeader.toScala, fileName.toScala, contentType.toScala)
      .asJava
  }

  @ApiMayChange
  def ofSource(
      entityLength: Optional[Long],
      getSource: RangeResults.SourceFunction,
      rangeHeader: OptString,
      fileName: OptString,
      contentType: OptString
  ): Result = {
    val getSourceAsScala: Long => (Long, ScalaSource) = { offset =>
      val result = getSource(offset)
      (result.getOffset, result.getSource.asScala)
    }
    RangeResult
      .ofSource(entityLength.toScala, getSourceAsScala, rangeHeader.toScala, fileName.toScala, contentType.toScala)
      .asJava
  }
}
