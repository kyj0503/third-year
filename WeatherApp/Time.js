export function getFormattedDate(date = new Date()) {
    return date.toISOString().slice(0, 10).replace(/-/g, '');
  }
  
  export function getKoreanTime() {
    const now = new Date();
    const koreanTimeOffset = 9 * 60; // 9 hours in minutes
    now.setMinutes(now.getMinutes() + koreanTimeOffset);
    return now;
  }
  
  export function getClosestBaseTime(baseTimes) {
    const now = getKoreanTime();
    const currentHour = now.getUTCHours();
  
    let closestBaseTime = baseTimes[0];
  
    for (let i = baseTimes.length - 1; i >= 0; i--) {
      const baseHour = parseInt(baseTimes[i].substring(0, 2), 10);
      if (currentHour >= baseHour) {
        closestBaseTime = baseTimes[i];
        break;
      }
    }
  
    return {
      baseDate: getFormattedDate(now),
      baseTime: closestBaseTime
    };
  }
  