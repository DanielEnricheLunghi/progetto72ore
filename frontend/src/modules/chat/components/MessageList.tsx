import React, { useEffect, useRef } from 'react';
import { MessageDto } from '../types';

interface Props {
  messages: MessageDto[];
}

const MessageList: React.FC<Props> = ({ messages }) => {
  const bottomRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages]);

  return (
    <div className="p-4 flex-1 overflow-y-auto">
      {messages.map(msg => (
        <div key={msg.id} className="mb-2">
          <span className="font-semibold">{msg.senderName}: </span>
          <span>{msg.content}</span>
        </div>
      ))}
      <div ref={bottomRef} />
    </div>
  );
};

export default MessageList;
